<?php


if (isset($_GET['day'])) {
	selectAndProcessDay($_GET['day']);
}

function selectAndProcessDay(string $day)
{
	$file = fopen("day".$day."/input.txt", 'r');
	$input = fread($file, filesize("day".$day."/input.txt"));
	fclose($file);

	require_once('day'.$day.'/Day'.$day.'.php');
	$day = "Day".$day;
	$sol = new $day($input);
	print_r("Your solution for <b>puzzle 1</b> is: ". $sol->answer1);
	print_r("<br />Your solution for <b>puzzle 2</b> is: ". $sol->answer2);
}