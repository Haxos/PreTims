<!DOCTYPE html>
<?php
$navNames = ['Plats', 'Utilisateurs', 'Connexion', 'Deconnexion'];
 ?>
<html lang="fr">
	<head>
    <!--
    Auteur : Adrian MAYO CARTES
    Date : 09.05.2017
    Description : template that will be used in all the pages
    -->
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Tims</title>
		{!! Html::style('https://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css') !!}
		{!! Html::style('https://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css') !!}
		<!--[if lt IE 9]>
			{{ Html::style('https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js') }}
			{{ Html::style('https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js') }}
		<![endif]-->
		<style> textarea { resize: none; } </style>
		{{ Html::style('css/customCSS.css') }}
	</head>
	<body>
		<header class="jumbotron">
		</header>
		<nav class="nav navbar-inverse">
			<ul class="nav col-sm-offset-3 col-sm-6">
				<li>
					{!! link_to_route('dish.index', $navNames[0], null,['class' => 'col-sm-6']) !!}
				</li>
				<li >
					{!! link_to_route('user.index', $navNames[1], null,['class' => 'col-sm-6']) !!}
				</li>
			</ul>
			@if(Auth::check())
				<div class="btn-group pull-right">
					{!! Form::open(['method' => 'POST', 'route' => ['logout']]) !!}
							{!! Form::submit( 'Deconnexion', ['class' => 'btn btn-warning']) !!}
					{!! Form::close() !!}
				</div>
			@else
				{!! link_to('login', 'Se connecter', ['class' => 'btn btn-info pull-right']) !!}
			@endif
		</nav>
		@yield('contenu')
	</body>
</html>
