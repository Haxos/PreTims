<?php
/**
 * ETML
 * Auteur : Adrian MAYO CARTES
 * Date : 09.05.2017
 * Description : file that will be migrate into a pivot table which will link the
 *  tables "t_user" and "t_dish"
 */
use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateTDishUserTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
      //create table
      Schema::create('t_dishUser', function (Blueprint $table)
      {
        //create fields
        $table->integer('fkDish')->unsigned()->index();
        $table->integer('fkUser')->unsigned()->index();
        $table->date('disUseDate');
        $table->timestamps();

        //select engine
        $table->engine = 'InnoDB';

        //create relation
        $table->foreign('fkDish')->references('idDish')->on('t_dish')
          ->onDelete('restrict')
          ->onUpdate('restrict');
        $table->foreign('fkUser')->references('idUser')->on('t_user')
          ->onDelete('restrict')
          ->onUpdate('restrict');
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
      Schema::drop('t_dishUser', function(Blueprint $table)
      {
        $table->dropForeign('t_dishUser_fkDish_foreign');
        $table->dropForeign('t_dishUser_fkUser_foreign');
      });
    }
}
