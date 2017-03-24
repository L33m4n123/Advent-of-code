<?php

class Day05
{
	public function __construct ($input)
	{
		$this->getPasswords($input);
	}

	protected function getPasswords($input)
	{
		$pass1 = $pass2 = '';
		$done = false;
		$counter = 0;

		while (!$done)
		{
			$pass = $input.''.$counter;
			$hash = md5($pass);
			$check = substr($hash, 0, 5);
			$pos = substr($hash, 5, 1);
			if ($check === "00000")
			{
				if (strlen($pass1) < 8)
				{
					$pass1 .= $hash[5];
				}
				if (sizeof($pass2) < 8 && is_numeric($pos) && (int) $pos <= 7)
				{
					$pos = (int) $pos;
					if (!isset($pass2[$pos]))
						$pass2[$pos] = $hash[6];
				}
				if (strlen($pass1) == 8 && sizeof($pass2) == 8)
				{
					$done = true;
				}
			}
			$counter++;
		}

		$this->answer1 = $pass1;
		ksort($pass2);
		$this->answer2 = implode('', $pass2);
	}
}