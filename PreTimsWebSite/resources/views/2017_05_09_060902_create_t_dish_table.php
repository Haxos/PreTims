<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 09.05.2017
 * Description : file for the entity "dish" that will be migrate
 */
use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateTDishTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
      //create table
      Schema::create('t_dish', function (Blueprint $table)
      {
        //create fields
        $table->increments('idDish');
        $table->string('disName')->unique();
        $table->text('disComposition');
        $table->text('disDescription');
        $table->string('disImage');
        $table->timestamps();

        //select engine
        $table->engine = 'InnoDB';
      });

    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
      //drop table
      Schema::drop('t_dish');
    }
}
