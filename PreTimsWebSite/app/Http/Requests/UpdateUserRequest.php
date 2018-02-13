<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class UpdateUserRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
      //get the user's id
      $id = $this->user;

      return [
        'useLogin' => 'required|unique:t_user,useLogin,'.$id.",idUser",
        'useName' => 'required',
        'useFirstName' => 'required',
        'useEmail' => 'required|email',
        'password' => 'nullable|min:6|confirmed',
        'usePrivilege' => 'required'
      ];
    }
}
