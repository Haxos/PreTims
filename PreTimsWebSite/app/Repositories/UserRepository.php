<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 09.05.2017
 * Description : Contain methods for all that is link to the model "User"
 */
namespace App\Repositories;

use App\Models\User;

class UserRepository
{
  protected $user;

  public function __construct(User $user)
	{
		$this->user = $user;
	}

  public function getPaginate($n)
  {
    return $this->user->paginate($n);
  }

  public function getById($id)
  {
    return $this->user->findOrFail($id);
  }

  public function getAll()
  {
    return $this->user->all();
  }

  public function store($request)
  {
    return $this->user->create($inputs);
  }

  public function update($request, $id)
  {
    $this->getById($id)->update($inputs);
  }

  public function destroy($id)
  {
    $this->getById($id)->delete();
  }
}
