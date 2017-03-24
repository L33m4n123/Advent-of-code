<?php

class Day07
{
	public function __construct($input)
	{
		$answers = $this->process($input);
		$this->answer1 = $answers[0];
		$this->answer2 = $answers[1];
	}

	protected function process($input) : array
	{
		$ipAdresses = preg_split("/((\r?\n)|(\r\n?))/", $input);
		$counter = array_fill(0, 2, 0);
		foreach ($ipAdresses as $ipAdress)
		{
			preg_match_all('/(?:\[\w*\])/', $ipAdress, $hypernetSequences);
			$supernetSequences = preg_split('/(?:\[\w*\])/', $ipAdress);
			$hypernetSequences = $hypernetSequences[0];
			for ($i=0; $i < sizeOf($hypernetSequences); $i++) { 
				$hypernetSequences[$i] = ltrim(rtrim($hypernetSequences[$i], ']'), '[');
			}
			// Now we need to check if the hypernetSequences
			// contains an abba structure
			// Part 1
			
			if (!$this->checkABBA($hypernetSequences))
				// Now we need to check if the supernetSequences
				// contains an abba structure aswell
				if ($this->checkABBA($supernetSequences))
					$counter[0]++;

			// Part 2
			// Check if the hypernetSequences contains
			// an BAB Structure
			$bab = $this->checkBAB($hypernetSequences);
			if (sizeOf($bab) > 0)
				// Now we need to check if the supernetSequences
				// contains an aba structure based on bab
				if ($this->checkABA($supernetSequences, $bab))
					$counter[1]++;

		}
		return $counter;
	}

	protected function checkABBA(array $sequences) : bool
	{
		foreach ($sequences as $key => $value)
		{
			$split = str_split($value);
			$check = array_fill(0, 4, '');
			foreach ($split as $char)
			{
				array_shift($check);
				array_push($check, $char);
				if ($check[0] === $check[3]
					&& $check[1] === $check[2]
					&& $check[0] != $check[1])
					return true;
			}
		}
		return false;
	}

	protected function checkBAB(array $sequences) : array
	{
		$answer = [];
		foreach ($sequences as $key => $value)
		{
			$split = str_split($value);
			$check = array_fill(0, 3, '');
			foreach ($split as $char)
			{
				array_shift($check);
				array_push($check, $char);
				if ($check[0] === $check[2] && $check[1] != $check[2])
					array_push($answer, $check);
			}
		}
		return $answer;
	}

	protected function checkABA(array $sequences, array $bab)
	{
		$answer = [];
		//var_dump($bab);
		foreach ($sequences as $key => $value)
		{
			$split = str_split($value);
			$check = array_fill(0, 3, '');
			foreach ($split as $char)
			{
				array_shift($check);
				array_push($check, $char);
				foreach ($bab as $key => $bba)
				{
					if ($check[0] === $bba[1] && $check[1] == $bba[0]
						&& $check[2] == $bba[1])
							return true;
				}
			}
		}
		return false;
	}
}