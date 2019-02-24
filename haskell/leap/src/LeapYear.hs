module LeapYear (isLeapYear) where


isLeapYear :: Integer -> Bool
isLeapYear year | hasFactor (400) = True
                | hasFactor (100) = False
                | otherwise       = hasFactor 4
  where hasFactor n = year `rem` n == 0
