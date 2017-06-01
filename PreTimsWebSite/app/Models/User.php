<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 09.05.2017
 * Description : Model of the entity "user" that will get the datas from the
 *  table "t_user"
 */
namespace App\Models;

use Illuminate\Notifications\Notifiable;
use Illuminate\Foundation\Auth\User as Authenticatable;

class User extends Authenticatable
{
    use Notifiable;

    protected $table = 't_user';
    protected $primaryKey = 'idUser';
    protected $fillable = ['useName', 'useFirstName', 'useEmail', 'password', 'useRight'];
    protected $hidden = ['password', 'remember_token'];

    public function dish()
    {
      return $this->belongsToMany('App\Models\Dish', 't_dishUser', 'fkuser', 'fkdish');
    }
}
