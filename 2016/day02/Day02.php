<?php

class Day02
{
	public function __construct($input)
	{
		
		$this->solveOne($input);
		$this->answer1 = implode('', $this->answer1);
		$this->solveTwo($input);
		$this->answer2 = implode('', $this->answer2);
	}

	protected function solveTwo($input)
	{

		// Start Position for second puzzle
		$x = 0;
		$y = 2;

		// keypad for second puzzle
		$keypad[0][0] = "INVALID";
		$keypad[0][1] = "INVALID";
		$keypad[0][2] = 1;
		$keypad[0][3] = "INVALID";
		$keypad[0][4] = "INVALID";

		$keypad[1][0] = "INVALID";
		$keypad[1][1] = 2;
		$keypad[1][2] = 3;
		$keypad[1][3] = 4;
		$keypad[1][4] = "INVALID";

		$keypad[2][0] = 5;
		$keypad[2][1] = 6;
		$keypad[2][2] = 7;
		$keypad[2][3] = 8;
		$keypad[2][4] = 9;

		$keypad[3][0] = "INVALID";
		$keypad[3][1] = "A";
		$keypad[3][2] = "B";
		$keypad[3][3] = "C";
		$keypad[3][4] = "INVALID";
		
		$keypad[4][0] = "INVALID";
		$keypad[4][1] = "INVALID";
		$keypad[4][2] = "D";
		$keypad[4][3] = "INVALID";
		$keypad[4][4] = "INVALID";

		$this->answer2 = [];

		foreach(preg_split("/((\r?\n)|(\r\n?))/", $input) as $line){
			$instructions = str_split($line);
			foreach ($instructions as $instruction)
			{
				switch($instruction)
				{
					case "U":
						if ($y > 0 && $keypad[$y-1][$x] != "INVALID") {
							$y = $y-1;
						}
						break;
					case "D":
						if ($y < 4 && $keypad[$y+1][$x] != "INVALID") {
							$y = $y+1;
						}
						break;
					case "L":
						if ($x > 0 && $keypad[$y][$x-1] != "INVALID") {
							$x = $x-1;
						}
						break;
					case "R":
						if ($x < 4 && $keypad[$y][$x+1] != "INVALID") {
							$x = $x+1;
						}
						break;
				}
			}
			array_push($this->answer2, $keypad[$y][$x]);
		} 

	}

	protected function solveOne($input)
	{
		$x = 1;
		$y = 1;

		// keypad[$y][$x]
		$keypad[0][0] = 1;
		$keypad[0][1] = 2;
		$keypad[0][2] = 3;

		$keypad[1][0] = 4;
		$keypad[1][1] = 5;
		$keypad[1][2] = 6;

		$keypad[2][0] = 7;
		$keypad[2][1] = 8;
		$keypad[2][2] = 9;

		$this->answer1 = [];

		foreach(preg_split("/((\r?\n)|(\r\n?))/", $input) as $line){
			$instructions = str_split($line);
			foreach ($instructions as $instruction)
			{
				switch($instruction)
				{
					case "U":
						$y = $y > 0 ? $y-1 : $y;
						break;
					case "D":
						$y = $y < 2 ? $y+1 : $y;
						break;
					case "L":
						$x = $x > 0 ? $x-1 : $x;
						break;
					case "R":
						$x = $x < 2 ? $x+1 : $x;
						break;
				}
			}
			array_push($this->answer1, $keypad[$y][$x]);
		} 

	}
}