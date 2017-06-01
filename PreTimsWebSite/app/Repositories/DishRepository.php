<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 11.05.2017
 * Description : Contain methods for all that is link to the model "Dish"
 */
namespace App\Repositories;

use App\Models\Dish;

use Illuminate\Support\Facades\Storage;
use Illuminate\Http\File;

class DishRepository
{
  protected $dish;

  public function __construct(Dish $dish)
	{
		$this->dish = $dish;
	}

  public function getPaginate($n)
  {
    return $this->dish->paginate($n);
  }

  public function getById($id)
  {
    return $this->dish->findOrFail($id);
  }

  public function getAll()
  {
    return $this->dish->all();
  }

  public function getValuesById($id)
  {
    $dish = $this->getById($id);
    $urlImage = Storage::disk('images')->url($dish['disImage']);
    $dish['disImage'] = $urlImage;
    return $dish;
  }

  public function getNbReservation()
  {
    //get all dishes
    $dishes = $this->getAll();

    $nbReservationPerName = null;

    //for each dish, count the number of reservation per clients
    foreach ($dishes as $dish)
    {
      $nbReservationPerName[$dish->disName] = $dish->client->count();
    }
    return $nbReservationPerName;
  }

  private function putNewImage($image)
  {
    //put new image on storage
    $newNameImage = Storage::disk('images')->put('', $image);
    return $newNameImage;
  }

  private function deleteImage($imageName)
  {
    //if the image exists on storage
    if(Storage::disk('images')->exists($imageName))
    {
      //delete image
      Storage::disk('images')->delete($imageName);
    }
  }

  public function store($request)
  {
    //store the image
    $inputs = $request->except('disImage');
    $image = $request->file('disImage');
    $inputs['disImage'] = $this->putNewImage($image);

    //create a new entry
    return $this->dish->create($inputs);
  }

  public function update($request, $id)
  {
    $inputs = $request->except('disImage');

    //if this image is not null
    if($request->get('disImage') != null)
    {
      //delete the old image
      $oldImageName = $this->getById($id)->disImage;
      $this->deleteImage($oldImageName);

      //put new image
      $newImage = $request->file('disImage');
      $inputs['disImage'] = $this->putNewImage($newImage);
    }

    //update the entry
    $this->getById($id)->update($inputs);
  }

  public function destroy($id)
  {
    $dish = $this->getById($id);
    $name = $dish->disName;

    //delete image
    $this->deleteImage($dish->disImage);

    //delete relations
    $dish->client()->detach();

    //delete entry
    $dish->delete();

    return $name;
  }
}
