<!--
Auteur : Adrian MAYO CARTES
Date : 11.05.2017
Description : content of the page that permit to add a new user
-->
@extends('layouts.app')

@section('content')
	<br>
	<div class="col-sm-offset-3 col-sm-6">
		<div class="panel panel-custom">
			<div class="panel-heading">Modifier un utilisateur</div>
			<div class="panel-body">
				{{ Form::open(['route' => ['user.update', $user->idUser], 'method' => 'put', 'enctype' => 'multipart/form-data']) }}
					<div class="form-group {{ $errors->has('useLogin') ? 'has-error' : '' }}">
						{{ Form::label('Login (*) :')}}
						{{ Form::text('useLogin', $user['useLogin'], ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('useLogin', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('useName') ? 'has-error' : '' }}">
						{{ Form::label('Nom (*) :')}}
						{{ Form::text('useName', $user['useName'], ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('useName', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('useFirstName') ? 'has-error' : '' }}">
						{{ Form::label('Prénom (*) :')}}
						{{ Form::text('useFirstName', $user['useFirstName'], ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('useFirstName', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('useEmail') ? 'has-error' : '' }}">
						{{ Form::label('Email (*) :')}}
						{{ Form::text('useEmail', $user['useEmail'], ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('useEmail', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('password') ? 'has-error' : '' }}">
						{{ Form::label('Nouveau mot de passe :')}}
						{{ Form::password('password', ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('password', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('password_confirmation') ? 'has-error' : '' }}">
						{{ Form::label('Confirmer le nouveau mot de passe :')}}
						{{ Form::password('password_confirmation', ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('password_confirmation', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('usePrivilege') ? 'has-error' : '' }}">
						{{ Form::label('Privilège (*) :')}}
						{{ Form::select('usePrivilege', ['0' => 'Client', '1' => 'Cuisinier', '2' => 'Administrateur'], $user['usePrivilege'], ['class' => 'form-control']) }}
						<small class="help-block">{{ $errors->first('usePrivilege', ':message') }}</small>
					</div>
					{{ Form::submit('Envoyer !', ['class' => 'btn btn-info pull-right']) }}
				{{ Form::close() }}
				<p>Les valeurs marquées de ce symbole (*) sont obligatoires</p>
			</div>
		</div>
		<a href="javascript:history.back()" class="btn btn-primary">
			<span class="glyphicon glyphicon-circle-arrow-left"></span> Retour
		</a>
	</div>
@endsection
