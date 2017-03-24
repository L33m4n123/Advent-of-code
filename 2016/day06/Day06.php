<?php

class Day06
{
	public function __construct($input)
	{
		$this->process($input);
	}

	protected function process($input)
	{
		$lines = preg_split("/((\r?\n)|(\r\n?))/", $input);
		$answers = array_fill(0, strlen($lines[0]), '');
		$answer1='';
		$answer2='';
		foreach($lines as $line)
		{
			$counter = 0;
			foreach (str_split($line) as $char)
			{
				$answers[$counter] .= $char;
				$counter++;
			}
		}
		foreach ($answers as $answer) {
			$arr = count_chars($answer, 1);
			arsort($arr);
			$answer1 .= chr(key($arr));
			asort($arr);
			$answer2 .=chr(key($arr));
		}

		$this->answer1 = $answer1;
		$this->answer2 = $answer2;
	}
}