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
Route::get('/login', 'Auth\LoginController@showLoginForm');
Route::post('/login', 'Auth\LoginController@login')->name('login');
Route::post('/logout', 'Auth\LoginController@logout')->name('logout');

Route::group(['middleware' => 'auth'], function(){
  Route::get('/', 'Web\DishController@index');
  Route::resource('dish', 'Web\DishController');
  Route::resource('user', 'Web\UserController');
});

//Auth::routes();

//Route::get('/home', 'HomeController@index')->name('home');
