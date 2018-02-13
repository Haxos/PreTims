<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 11.05.2017
 * Description : Controller used for all that is related to the dishes
 */
namespace App\Http\Controllers\Web;

use App\Repositories\DishRepository;
use App\Http\Controllers\Controller;
use App\Http\Requests\CreateDishRequest;
use App\Http\Requests\UpdateDishRequest;
//use Illuminate\Http\Request;

class DishController extends Controller
{
    protected $dishRepository;
    protected $nbPerPage = 4;

    public function __construct(DishRepository $dishRepository)
    {
      $this->dishRepository = $dishRepository;
    }
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
      $dishes = $this->dishRepository->getPaginate($this->nbPerPage);
      $links = $dishes->render();
      $nbReservation = $this->dishRepository->getNbReservation();
      return view('dish.list', compact('dishes', 'links', 'nbReservation'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
      return view('dish.add');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(CreateDishRequest $request)
    {
      $dish = $this->dishRepository->store($request);

      return redirect('dish')->withOk('Le plat "'.$dish->disName.'" a bien été enregistré');
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
      $dish = $this->dishRepository->getValuesById($id);
      return view('dish.modify', compact('dish'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(UpdateDishRequest $request, $id)
    {
      $this->dishRepository->update($request, $id);

      return redirect('dish')->withOk('Le plat "'.$request->input('disName').'" a été mis à jour');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
      $name = $this->dishRepository->destroy($id);

      return redirect('dish')->withOk('Le plat "'.$name.'" a bien été supprimé');
    }
}
