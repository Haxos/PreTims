<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use Illuminate\Foundation\Auth\AuthenticatesUsers;
use Illuminate\Support\Facades\Auth;
use Illuminate\Http\Request;

use Tymon\JWTAuth\Exceptions\JWTExeption;
use Tymon\JWTAuth\Facades\JWTAuth;
use Symfony\Component\HttpFoundation\Response;

class LoginController extends Controller
{
    /*
    |--------------------------------------------------------------------------
    | Login Controller
    |--------------------------------------------------------------------------
    |
    | This controller handles authenticating users for the application and
    | redirecting them to your home screen. The controller uses a trait
    | to conveniently provide its functionality to your applications.
    |
    */

    use AuthenticatesUsers;

    /**
     * Where to redirect users after login.
     *
     * @var string
     */
    protected $redirectTo = '/dish';

    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('guest')->except('logout');
    }

    public function username()
    {
      return 'useLogin';
    }

    protected function redirectTo()
    {
      $user = Auth::user();

      //if it is a client then logout
      if($user->usePrivilege == 0)
      {
        Auth::logout();
        return '/';
      }
      else
      {
        return '/dish';
      }
    }

    /**
     * Authentificate from the Api route
     */
    public function authentificateWithJWT(Request $request)
    {
      //get the user credentials
      $credentials = $request->json()->all();

      try
      {
          //return an error message in case of wrong credentials
          if(!$token = JWTAuth::attempt($credentials))
          {
            $json = ['error' => 'User credentials are invalid'];
            return response($json, 418);
          }
      }
      catch(JWTException $e)
      {
        //return an error in case of exception from JWT
        $json = ['error' => 'Something went wrong'];
        return response($json, 520);
      }

      //return the token
      $json = ['token' => $token];
      return response($json, 200);
    }
}
