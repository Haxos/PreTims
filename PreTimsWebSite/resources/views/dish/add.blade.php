<!--
Auteur : Adrian MAYO CARTES
Date : 10.05.2017
Description : content of the page that permit to add a new dish
-->
@extends('layouts.app')

@section('content')
	<br>
	<div class="col-sm-offset-3 col-sm-6">
		<div class="panel panel-custom">
			<div class="panel-heading">Ajouter un nouveau plat</div>
			<div class="panel-body">
				{{ Form::open(['route' => 'dish.store', 'enctype' => 'multipart/form-data']) }}
					<div class="form-group {{ $errors->has('disName') ? 'has-error' : '' }}">
						{{ Form::label('Nom (*) :')}}
						{{ Form::text('disName', null, ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('disName', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('disComposition') ? 'has-error' : '' }}">
						{{ Form::label('Composition (*) :')}}
						{{ Form::textarea('disComposition', null, ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('disComposition', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('disDescription') ? 'has-error' : '' }}">
						{{ Form::label('Description (*) :')}}
						{{ Form::textarea('disDescription', null, ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('disDescription', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('disImage') ? 'has-error' : '' }}">
						{{ Form::label('Image (*) :')}}
						{{ Form::file('disImage') }}
						<small class="help-block">{{ $errors->first('disImage', ':message') }}</small>
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
