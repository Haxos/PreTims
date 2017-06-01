<!--
Auteur : Adrian MAYO CARTES
Date : 09.05.2017
Description : content of the page that display the dishes
-->
@extends('layouts.app')

@section('content')
    <br>
    <div class="col-sm-offset-3 col-sm-6">
    @if(session()->has('ok'))
			<div class="alert alert-success alert-dismissible">{{ session('ok') }}</div>
    @elseif(session()->has('error'))
      <div class="alert alert-danger alert-dismissible">{{ session('error') }}</div>
		@endif
		<div class="panel panel-custom">
			<div class="panel-heading" >
				<h3 class="panel-title"></h3>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nom</th>
						<!--<th></th>-->
						<th></th>
						<th></th>
            <th>Nb Reservation</th>
					</tr>
				</thead>
				<tbody>
					@foreach ($dishes as $dish)
						<tr>
							<td>{{ $dish->idDish }}</td>
							<td class="text-primary"><strong>{{ $dish->disName }}</strong></td>
							<!--<td>{{ link_to_route('dish.show', 'Voir', [$dish->idDish], ['class' => 'btn btn-success btn-block']) }}</td>-->
							<td>{{ link_to_route('dish.edit', 'Modifier', [$dish->idDish], ['class' => 'btn btn-warning btn-block']) }}</td>
							<td>
								{{ Form::open(['method' => 'DELETE', 'route' => ['dish.destroy', $dish->idDish]]) }}
									{{ Form::submit('Supprimer', ['class' => 'btn btn-danger btn-block', 'onclick' => 'return confirm(\'Vraiment supprimer ce plat ?\')']) }}
								{{ Form::close() }}
							</td>
              <td>
                {{$nbReservation[$dish->disName]}}
              </td>
						</tr>
					@endforeach
        </tbody>
			</table>
		</div>
		{{ link_to_route('dish.create', 'Ajouter un plat', [], ['class' => 'btn btn-info pull-right']) }}
		{{ $links }}
	</div>
@endsection
