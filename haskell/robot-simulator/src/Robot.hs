module Robot
    ( Bearing(East,North,South,West)
    , bearing
    , coordinates
    , mkRobot
    , move
    ) where

data Bearing = North
             | East
             | South
             | West
             deriving (Eq, Show)

type Position = (Integer, Integer)
data Robot = Robot Position Bearing

bearing :: Robot -> Bearing
bearing (Robot p b) = b

coordinates :: Robot -> (Integer, Integer)
coordinates (Robot p b) = p

mkRobot :: Bearing -> (Integer, Integer) -> Robot
mkRobot direction coordinates = Robot coordinates direction

turnLeft b = case b of
  North -> West
  East  -> North
  South -> East
  West  -> South

turnRight b = case b of
  North -> East
  East  -> South
  South -> West
  West  -> North

turn :: Robot -> Char -> Robot
turn (Robot p b) newB = case newB of
  'L' -> Robot p (turnLeft b)
  'R' -> Robot p (turnRight b)
  _   -> Robot p b

advance :: Robot -> Robot
advance (Robot (x, y) b) = case b of
  North -> Robot (x, y + 1) b
  East  -> Robot (x + 1, y) b
  South -> Robot (x, y - 1) b
  West  -> Robot (x - 1, y) b

move :: Robot -> String -> Robot
move robot instructions = foldl foldFn robot instructions
 where
  foldFn robot instruction | instruction == 'A' = advance robot
                           | otherwise          = turn robot instruction
