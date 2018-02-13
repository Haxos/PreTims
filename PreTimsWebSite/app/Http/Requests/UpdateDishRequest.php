<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 11.05.2017
 * Description : Validate the different field entred by the user
 */
namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class UpdateDishRequest extends FormRequest
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
      //get the dish's id
      $id = $this->dish;

      return [
        'disName' => 'required|max:255|unique:t_dish,disName,'.$id.',idDish',
        'disComposition' => 'required',
        'disDescription' => 'required',
        'disImage' => 'image'
      ];
    }
}
