module LeapYear (isLeapYear) where

{- iter: 1 -}
{- isLeapYear :: Integer -> Bool -}
{- isLeapYear year | hasFactor 400 = True -}
                {- | hasFactor 100 = False -}
                {- | otherwise       = hasFactor 4 -}
  {- where hasFactor n = year `rem` n == 0 -}

{- iter:2  -}
isLeapYear year | isNotDivisibleBy 4   = False
                | isNotDivisibleBy 100 = True
                | isNotDivisibleBy 400 = False
                | otherwise            = True
  where isNotDivisibleBy n = year `rem` n /= 0

