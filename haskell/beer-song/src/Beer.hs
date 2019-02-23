module Beer (song) where

song :: String
song = concat $ sing 99

firstPhrase :: Int -> String
firstPhrase count | count == 0 = "No more bottles of beer on the wall, "
                  | count == 1 = "1 bottle of beer on the wall, "
                  | otherwise = (show count) ++ " bottles of beer on the wall, "
secondPhrase count | count == 0 = "no more bottles of beer.\n"
                   | count == 1 = "1 bottle of beer.\n"
                   | otherwise  = (show count) ++ " bottles of beer.\n"
thirdPhrase count | count == 0 = "Go to the store and buy some more, "
                  | count == 1 = "Take it down and pass it around, "
                  | otherwise  = "Take one down and pass it around, "
fourthPhrase count
  | count == 0 = "99 bottles of beer on the wall.\n"
  | count == 1 = "no more bottles of beer on the wall.\n"
  | count == 2 = "1 bottle of beer on the wall.\n"
  | otherwise  = (show (count - 1)) ++ " bottles of beer on the wall.\n"

newLine count | count == 0 = ""
              | otherwise  = "\n"

sing :: Int -> [String]
sing count
  | count < 0
  = [""]
  | otherwise
  = (   [firstPhrase, secondPhrase, thirdPhrase, fourthPhrase, newLine]
    <*> [count]
    )
    ++ sing (count - 1)

