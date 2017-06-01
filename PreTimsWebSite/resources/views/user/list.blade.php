<!--
Auteur : Adrian MAYO CARTES
Date : 09.05.2017
Description : content of the page that display the users
-->
@extends('template')

@section('contenu')
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
						<th>#</th>
						<th>Nom</th>
						<!--<th></th>-->
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					@foreach ($users as $user)
						<tr>
							<td>{{ $user->idUser }}</td>
							<td class="text-primary"><strong>{{ $user->useName }}</strong></td>
							<!--<td>{{ link_to_route('user.show', 'Voir', [$user->idUser], ['class' => 'btn btn-success btn-block']) }}</td>-->
							<td>{{ link_to_route('user.edit', 'Modifier', [$user->idUser], ['class' => 'btn btn-warning btn-block']) }}</td>
							<td>
								{{ Form::open(['method' => 'DELETE', 'route' => ['user.destroy', $user->idUser]]) }}
									{{ Form::submit('Supprimer', ['class' => 'btn btn-danger btn-block', 'onclick' => 'return confirm(\'Vraiment supprimer ce plat ?\')']) }}
								{{ Form::close() }}
							</td>
						</tr>
					@endforeach
        </tbody>
			</table>
		</div>
		{{ link_to_route('user.create', 'Ajouter un utilisateur', [], ['class' => 'btn btn-info pull-right']) }}
		{{ $links }}
	</div>
@endsection
