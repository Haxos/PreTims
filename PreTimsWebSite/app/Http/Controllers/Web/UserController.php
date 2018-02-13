<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 09.05.2017
 * Description : Controller used for all that is related to the users
 */
namespace App\Http\Controllers\Web;

use App\Repositories\UserRepository;
use App\Http\Controllers\Controller;
use App\Http\Requests\CreateUserRequest;
use App\Http\Requests\UpdateUserRequest;
//use Illuminate\Http\Request;

class UserController extends Controller
{
  protected $userRepository;
  protected $nbPerPage = 5;

  public function __construct(UserRepository $userRepository)
  {
    $this->userRepository = $userRepository;
  }
  /**
   * Display a listing of the resource.
   *
   * @return \Illuminate\Http\Response
   */
  public function index()
  {
    $users = $this->userRepository->getPaginate($this->nbPerPage);
    $links = $users->render();
    return view('user.list', compact('users', 'links', 'nbReservation'));
  }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
      return view('user.add');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(CreateUserRequest $request)
    {
      $user = $this->userRepository->store($request);

      return redirect('user')->withOk("L'utilisateur ".'"'.$user->useLogin.'" a bien été enregistré');
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
      $user = $this->userRepository->getById($id);

      return view('user.modify', compact('user'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(UpdateUserRequest $request, $id)
    {
      $this->userRepository->update($request, $id);

      return redirect('user')->withOk("L'utilisateur ".'"'.$request->input('useLogin').'" a été mis à jour');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
      $name = $this->userRepository->destroy($id);

      return redirect('user')->withOk("L'utilisateur ".'"'.$name.'" a bien été supprimé');
    }
}
