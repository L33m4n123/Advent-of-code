<?php

class Day01
{
	public function __construct($input)
	{
		// Some initialation 
		$this->dir = "N";		
		$this->yAxis = 0;
		$this->xAxis = 0;

		$this->visited = [];
		$this->answer2 = "";
		$this->found = false;


		// let's do this
		$this->processInput($input);
		$this->answer1 = abs($this->xAxis) + abs($this->yAxis);
	}

	protected function processInput($input)
	{
		$instructions = explode(', ', $input);

		foreach ($instructions as $instruction)
		{
			if(substr($instruction, 0, 1) == 'L')
			{
				$this->goLeft(substr($instruction, 1, strlen($instruction)));
			} else {
				$this->goRight(substr($instruction, 1, strlen($instruction)));
			}
		}
	}

	protected function goLeft(int $ammount)
	{
		switch($this->dir)
		{
			case "N":
				$this->dir = "W";
				while ($ammount > 0)
				{
					$ammount -= 1;
					$this->xAxis -= 1;
					if ($this->found == false && in_array($this->xAxis."|".$this->yAxis, $this->visited))
					{
						$this->found = true;
						$this->answer2 = abs($this->xAxis) + abs($this->yAxis);
					}
					else if ($this->found == false)
					{
						array_push($this->visited, $this->xAxis."|".$this->yAxis);
					}
				}
				break;
			case "E":
				$this->dir = "N";
				while ($ammount > 0)
				{
					$ammount -= 1;
					$this->yAxis += 1;
					if ($this->found == false && in_array($this->xAxis."|".$this->yAxis, $this->visited))
					{
						$this->found = true;
						$this->answer2 = abs($this->xAxis) + abs($this->yAxis);
					}
					else if ($this->found == false)
					{
						array_push($this->visited, $this->xAxis."|".$this->yAxis);
					}
				}
				break;
			case "S":
				$this->dir = "E";
				while ($ammount > 0)
				{
					$ammount -= 1;
					$this->xAxis += 1;
					if ($this->found == false && in_array($this->xAxis."|".$this->yAxis, $this->visited))
					{
						$this->found = true;
						$this->answer2 = abs($this->xAxis) + abs($this->yAxis);
					}
					else if ($this->found == false)
					{
						array_push($this->visited, $this->xAxis."|".$this->yAxis);
					}
				}
				break;
			case "W":
				$this->dir = "S";
				while ($ammount > 0)
				{
					$ammount -= 1;
					$this->yAxis -= 1;
					if ($this->found == false && in_array($this->xAxis."|".$this->yAxis, $this->visited))
					{
						$this->found = true;
						$this->answer2 = abs($this->xAxis) + abs($this->yAxis);
					}
					else if ($this->found == false)
					{
						array_push($this->visited, $this->xAxis."|".$this->yAxis);
					}
				}
				break;
		}
	}

	protected function goRight($ammount)
	{
		switch($this->dir)
		{
			case "N":
				$this->dir = "E";
				while ($ammount > 0)
				{
					$ammount -= 1;
					$this->xAxis += 1;
					if ($this->found == false && in_array($this->xAxis."|".$this->yAxis, $this->visited))
					{
						$this->found = true;
						$this->answer2 = abs($this->xAxis) + abs($this->yAxis);
					}
					else if ($this->found == false)
					{
						array_push($this->visited, $this->xAxis."|".$this->yAxis);
					}
				}
				break;
			case "E":
				$this->dir = "S";
				while ($ammount > 0)
				{
					$ammount -= 1;
					$this->yAxis -= 1;
					if ($this->found == false && in_array($this->xAxis."|".$this->yAxis, $this->visited))
					{
						$this->found = true;
						$this->answer2 = abs($this->xAxis) + abs($this->yAxis);
					}
					else if ($this->found == false)
					{
						array_push($this->visited, $this->xAxis."|".$this->yAxis);
					}
				}
				break;
			case "S":
				$this->dir = "W";
				while ($ammount > 0)
				{
					$ammount -= 1;
					$this->xAxis -= 1;
					if ($this->found == false && in_array($this->xAxis."|".$this->yAxis, $this->visited))
					{
						$this->found = true;
						$this->answer2 = abs($this->xAxis) + abs($this->yAxis);
					}
					else if ($this->found == false)
					{
						array_push($this->visited, $this->xAxis."|".$this->yAxis);
					}
				}
				break;
			case "W":
				$this->dir = "N";
				while ($ammount > 0)
				{
					$ammount -= 1;
					$this->yAxis += 1;
					if ($this->found == false && in_array($this->xAxis."|".$this->yAxis, $this->visited))
					{
						$this->found = true;
						$this->answer2 = abs($this->xAxis) + abs($this->yAxis);
					}
					else if ($this->found == false)
					{
						array_push($this->visited, $this->xAxis."|".$this->yAxis);
					}
				}
				break;
		}
	}
}