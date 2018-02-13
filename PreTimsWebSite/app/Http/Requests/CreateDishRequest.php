<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 10.05.2017
 * Description : Validate the different field entred by the user
 */
namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class CreateDishRequest extends FormRequest
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
        return [
            'disName' => 'required|unique:t_dish|max:255',
            'disComposition' => 'required',
            'disDescription' => 'required',
            'disImage' => 'required|image'
        ];
    }
}
