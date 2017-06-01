<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 09.05.2017
 * Description : Model of the entity "dish" that will get the datas from the
 *  table "t_dish"
 */
namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Dish extends Model
{
  //values of the table in the database
  protected $table = 't_dish';
  protected $primaryKey = 'idDish';
  protected $fillable = ['disName', 'disComposition', 'disDescription', 'disImage'];
  public $timestamps = true;

  //making relation
  public function client()
  {
    return $this->belongsToMany('App\Models\User', 't_dishUser', 'fkdish', 'fkuser');
  }
}
