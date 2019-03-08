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

turnLeft :: Robot -> Bearing -> Robot
turnLeft = error "hoge"

turnRight :: Robot -> Bearing -> Robot
turnRight = error "hoge"

advance :: Robot -> (Integer, Integer) -> Robot
advance = error "hoge"

move :: Robot -> String -> Robot
move robot instructions = error "hoge"
