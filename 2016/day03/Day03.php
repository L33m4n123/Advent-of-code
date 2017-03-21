<?php

class Day03
{
	public function __construct($input)
	{
		$this->answer1 = $this->solveFirstPart($input);
		$this->answer2 = $this->solveSecondPart($input);
	}

	protected function solveFirstPart($input)
	{
		$possible = 0;
		foreach(preg_split("/((\r?\n)|(\r\n?))/", $input) as $line)
		{
			// Is the triangle possible?
			$sides = preg_split('/( )+/', ltrim(rtrim($line)));
			sort($sides);
			if ($sides[0] + $sides[1] > $sides[2])
			{
				$possible +=1;
			}
		}

		return $possible;
	}

	protected function solveSecondPart($input)
	{
		$ammount = 0;
		$triangle1 = [];
		$triangle2 = [];
		$triangle3 = [];
		$possible = 0;
		foreach(preg_split("/((\r?\n)|(\r\n?))/", $input) as $line)
		{
			$ammount +=1;
			$sides = preg_split('/( )+/', ltrim(rtrim($line)));
			if ($sides[1] == '') {
				var_dump($ammount);
				var_dump($sides);
				break;
			}
			array_push($triangle1, $sides[0]);
			array_push($triangle2, $sides[1]);
			array_push($triangle3, $sides[2]);
			if ($ammount % 3 == 0)
			{
				sort($triangle1);
				sort($triangle2);
				sort($triangle3);
				if ($triangle1[0] +  $triangle1[1] > $triangle1[2])
					$possible +=1;
				
				if ($triangle2[0] +  $triangle2[1] > $triangle2[2])
					$possible +=1;

				if ($triangle3[0] +  $triangle3[1] > $triangle3[2])
					$possible +=1;

				$triangle1 = [];
				$triangle2 = [];
				$triangle3 = [];
			}
		}
		return $possible;
	}
}