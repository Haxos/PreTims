<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 09.05.2017
 * Description : file for the entity "user" that will be migrate
 */
use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateTUserTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
      //create table
      Schema::create('t_user', function (Blueprint $table)
      {
        //create fields
        $table->increments('idUser');
        $table->string('useLogin')->unique();
        $table->string('useName');
        $table->string('useFirstName');
        $table->string('useEmail')->unique();
        $table->string('password');
        $table->integer('usePrivilege')->default(0);
        $table->rememberToken();
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
      Schema::drop('t_user');
    }
}
