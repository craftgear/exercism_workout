module LeapYear (isLeapYear) where

isLeapYear :: Integer -> Bool
isLeapYear year = hasFactor 4 && (not (hasFactor 100) || hasFactor 400)
  where hasFactor n = year `rem` n == 0
