<!--
Auteur : Adrian MAYO CARTES
Date : 11.05.2017
Description : content of the page that permit to modify an existing dish
-->
@extends('template')

@section('contenu')
	<br>
	<div class="col-sm-offset-3 col-sm-6">
		<div class="panel panel-custom">
			<div class="panel-heading">Modification d'un plat</div>
			<div class="panel-body">
				{{ Form::open(['route' => ['dish.update', $dish->idDish], 'method' => 'put', 'enctype' => 'multipart/form-data']) }}
					<div class="form-group {{ $errors->has('disName') ? 'has-error' : '' }}">
						{{ Form::label('Nom (*) :')}}
						{{ Form::text('disName', $dish['disName'], ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('disName', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('disComposition') ? 'has-error' : '' }}">
						{{ Form::label('Composition (*) :')}}
						{{ Form::textarea('disComposition', $dish['disComposition'], ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('disComposition', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('disDescription') ? 'has-error' : '' }}">
						{{ Form::label('Description (*) :')}}
						{{ Form::textarea('disDescription', $dish['disDescription'], ['class' => 'form-control', 'placeholder' => '']) }}
						<small class="help-block">{{ $errors->first('disDescription', ':message') }}</small>
					</div>
					<div class="form-group {{ $errors->has('disImage') ? 'has-error' : '' }}">
						{{ Form::label('Image (*) :') }}
						<!--TODO: this <br> need to be remplace by a style-->
						<br/>
						{{ Html::image($dish['disImage'], 'Image not found', ['width' => '150px', 'height' => '150px']) }}
						<p>
							Si une nouvelle image est entrée, l'ancien sera automatiquement remplacée.
						</p>
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
