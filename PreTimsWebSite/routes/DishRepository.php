<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 09.05.2017
 * Description : Contain methods for all that is link to the model "Dish"
 */
namespace App\Repositories;

use App\Models\Dish;

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

  public function getNbReservation()
  {
    $dishes = $this->getAll();

    foreach ($dishes as $dish)
    {
      $nbReservationPerName[$dish->disName] = $dish->client->count();
    }
    return $nbReservationPerName;
  }

  public function store($inputs)
  {
    return $this->dish->create($inputs);
  }

  public function update($id, Array $inputs)
  {
    $this->getById($id)->update($inputs);
  }

  public function destroy($id)
  {
    $this->getById($id)->delete();
  }
}
