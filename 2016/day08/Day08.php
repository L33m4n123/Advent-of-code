<?php

class Day08
{
	public function __construct($input)
	{
		// first one y
		// second one x
		$this->keypad = array_fill(0, 6, array_fill(0, 50, 0));
		$answers = $this->process($input);
		$this->answer1 = $answers[0];
		$this->answer2 = "look below <br /><br /><br />";

		$this->answer2 .= $this->displayKeypad();
	}


	protected function displayKeypad()
	{
		$print = '';
		foreach ($this->keypad as $key => &$value)
		{
			foreach ($value as $k => &$val)
			{
				if ($val === 1)
					$val = '#';
				else
					$val = '..';
				$print .= $val;
			}
			$print .= '<br />';
		}
		return $print;
	}

	protected function process(string $input) : array
	{
		$commands = preg_split("/((\r?\n)|(\r\n?))/", $input);
		foreach ($commands as $command)
			$this->processCommand($command);

		$counter = 0;
		foreach ($this->keypad as $key => $value)
		{
			foreach ($value as $k => $v)
			{
				$counter+=$v;
			}
		}
		return [$counter,2];
	}

	protected function processCommand(string $command)
	{
		if (substr($command, 0, 4) === 'rect')
		{
			$area = [];
			$command = str_replace('rect ', '', $command);
			$area = explode('x', $command);
			$this->lightArea((int) $area[0], (int) $area[1]);
		}
		else if (substr($command, 0, 6) === 'rotate')
		{
			$command = str_replace('rotate ', '', $command);
			$command = str_replace(' by ', ',', $command);
			$split = preg_split('/\s/', $command);
			$option = $split[0];
			$range = preg_split('/,/', preg_split('/=/', $split[1])[1]);
			$this->rotating($option, (int) $range[0], (int) $range[1]);
		}
	}

	protected function lightArea(int $x, int $y)
	{
		for ($i=0; $i < $y; $i++)
			for ($j=0; $j < $x; $j++)
				$this->keypad[$i][$j] = 1;
	}

	protected function rotating(string $option, int $pos, int $ammount)
	{
		if ($option === 'row')
			for ($i=0; $i < $ammount; $i++)
				array_unshift($this->keypad[$pos], array_pop($this->keypad[$pos]));
		else if ($option === 'column')
		{
			$shift = '';
			foreach ($this->keypad as $key => $value)
				$shift .= $value[$pos];
			$shiftArr = str_split($shift);
			for ($i=0; $i < $ammount; $i++)
				array_unshift($shiftArr, array_pop($shiftArr));

			$counter = 0;
			foreach ($this->keypad as $key => &$value)
			{
				$value[$pos] = (int)$shiftArr[$counter];
				$counter++;
			}
		}
	}
}