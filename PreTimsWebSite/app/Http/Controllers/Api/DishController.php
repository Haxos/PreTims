<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 19.05.2017
 * Description : Controller used for all that is related with the dishes
 *    on the mobile app
 */
namespace App\Http\Controllers\Api;

use App\Repositories\DishRepository;
use App\Repositories\UserRepository;
use App\Http\Controllers\Controller;
//use App\Http\Requests\CreateDishRequest;
//use App\Http\Requests\UpdateDishRequest;
use Illuminate\Http\Request;

class DishController extends Controller
{
    protected $dishRepository;
    protected $userRepository;

    public function __construct(DishRepository $dishRepository, UserRepository $userRepository)
    {
      $this->dishRepository = $dishRepository;
      $this->userRepository = $userRepository;
    }
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
      $dishies = $this->dishRepository->getAll();

      //$json = json_encode($dishies, JSON_FORCE_OBJECT);
      foreach ($dishies as $dish)
      {
        $dish->disImage = $this->dishRepository->getImage($dish->disImage);
      }

      return response($dishies)->header("NumberDishies", $dishies->count());
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
      //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
      $values = $request->json()->all();

      $dish = $this->dishRepository->getById($values["idDish"]);
      $user = $this->userRepository->getByLogin($values["useLogin"]);
      $date = date("Y-m-d");

      $dish->client()->attach($user->idUser, ['disUseDate' => $date]);

      $json = ['message' => 'Reservation OK'];
      return response($json, 200);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
      //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
      //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
      $values = $request->json()->all();

      $dish = $this->dishRepository->getById($values["idDish"]);
      $user = $this->userRepository->getByLogin($values["useLogin"]);
      $date = $values["date"];

      $dish->client()
        ->wherePivot('disUseDate', '=', $date)
        ->detach();

      $json = ['message' => 'Reservation OK'];
      return response($json, 200);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
      //
    }
}
