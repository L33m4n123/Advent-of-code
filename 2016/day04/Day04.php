<?php

class Day04
{
	public function __construct($input)
	{
		$this->answer1 = $this->solveFirstPart($input);
		//$this->answer1 = 1;
		$this->answer2 = $this->solveSecondPart($input);
	}

	protected function solveFirstPart($input)
	{
		$answer = 0;
		$notpassed = [];
		// Split up the input into lines to get each room
		foreach(preg_split("/((\r?\n)|(\r\n?))/", $input) as $room)
		{
			/*
			* split up the information into roomName, sectorID and checksum
			* $split[0] up to $split[sizeOf($split-1)] are the roomName
			*/
			$split = explode('-', $room);
			$sectorIDandChecksum = array_pop($split);
			$roomName = implode('', $split);
			$split = explode('[', $sectorIDandChecksum);
			$sectorID = $split[0];
			$checksum = rtrim($split[1], ']');

			/*
			* Now need to count the different chars in the roomName
			*/
			$count = count_chars($roomName, 1);
			$newKeys = [];
			foreach (array_keys($count) as $key)
			{
				array_push($newKeys, chr($key));
			}
			$newCount = array_combine($newKeys, array_values($count));
			arsort($newCount);
			//var_dump($newCount);
			$newChecksum = $this->getChecksum($newCount);
			//var_dump($newChecksum);
			
			if ($newChecksum == $checksum)
				$answer+=$sectorID;
		}
		return $answer;
	}

	protected function solveSecondPart($input)
	{
		foreach(preg_split("/((\r?\n)|(\r\n?))/", $input) as $room)
		{
			/*
			* split up the information into roomName, sectorID and checksum
			* $split[0] up to $split[sizeOf($split-1)] are the roomName
			*/
			$split = explode('-', $room);
			$sectorIDandChecksum = array_pop($split);
			$roomName = implode(' ', $split);
			$split = explode('[', $sectorIDandChecksum);
			$sectorID = $split[0];
			$checksum = rtrim($split[1], ']');
			$decypheredRoomName = $this->decypher((int) $sectorID, $roomName);
			if ($decypheredRoomName == 'northpole object storage')
				return $sectorID;
		}
	}

	protected function getChecksum (array $array) : string
	{
		$checksum = '';
		$toCheck = '';
		$lastValue = -1;
		foreach ($array as $key => $value)
		{
			if ($lastValue != $value)
			{
				if ($toCheck != '')
				{
					// now toCheck contains of letters by same value
					$split = str_split($toCheck);
					sort($split);
					$split = implode('', $split);
					$checksum .= $split;
					$toCheck = '';
				}
				$toCheck .= $key;
				$lastValue = $value;
			} else
			{
				$toCheck .=$key;
			}
		}
		if ($toCheck != '')
		{
			$split = str_split($toCheck);
			sort($split);
			$split = implode('', $split);
			$checksum .= $split;
		}
		return substr($checksum,0,5);
	}

	protected function decypher (int $shift, string $cypher) : string
	{
		$decyphered = '';
		for ($i = 0; $i < strlen($cypher); $i ++)
		{
			$ascii = ord($cypher[$i]);
			// as it is all lowercase it will be between ord 97 and ord 122
			// however spaces should stay what they are
			if ($ascii != 32) {
				for ($j = 0; $j < $shift; $j++)
				{
					$ascii++;
					if ($ascii == 123)
						$ascii = 97;
				}
			}
			$decyphered .= chr($ascii);
		}
		return $decyphered;
	}
}
