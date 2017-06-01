<?php
/*
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 09.05.2017
 * Description : Will return, depending of the CRUD verb used and the URL address,
 *    the corrects values from the corresponding method from the controllers
 */

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

/*Route::get('/', function () {
    return view('welcome');
});*/

Route::get('/', 'DishController@index');

Route::resource('dish', 'DishController');
Route::resource('user', 'UserController');
