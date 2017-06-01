<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 12.05.2017
 * Description : Contain methods for all that is link to the model "User"
 */
namespace App\Repositories;

use App\Models\User;
use Illuminate\Support\Facades\Hash;

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

  private function hashPassword($password)
  {
    return Hash::make($password);
  }

  public function store($request)
  {
    $inputs = $request->all();

    $inputs['password'] = $this->hashPassword($inputs['password']);

    return $this->user->create($inputs);
  }

  public function update($request, $id)
  {
    $inputs = $request->except('password');

    //if we want to set a new password
    if($request->get('password') != null)
    {
      $inputs['password'] = $this->hashPassword($request->get('password'));
    }

    $this->getById($id)->update($inputs);
  }

  public function destroy($id)
  {
    //get user
    $user = $this->getById($id);
    $name = $user->useLogin;

    //delete relations
    $user->dish()->detach();

    //delette user
    $this->getById($id)->delete();

    return $name;
  }
}
