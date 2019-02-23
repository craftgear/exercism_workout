module DNA (toRNA) where

isDNA :: String -> Bool
isDNA xs = 'U' `elem` xs

toRNA :: String -> Either Char String
toRNA xs = if isDNA xs then Left 'U' else isInvalid $ translated
  where translated = foldr translate "" xs

isInvalid :: String -> Either Char String
isInvalid xs = case 'X' `elem` xs of
  True  -> Left 'X'
  False -> Right xs

translate :: Char -> String -> String
translate x accum = case x of
  'C'       -> 'G' : accum
  'G'       -> 'C' : accum
  'T'       -> 'A' : accum
  'A'       -> 'U' : accum
  otherwise -> "X"


