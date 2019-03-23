module DNA (toRNA) where

toRNA :: String -> Either Char String
toRNA xs = foldr helper (Right "") xs
 where
  helper x accum = case transcribe x of
    Right x   -> fmap (\xs -> x : xs) accum
    otherwise -> Left x

{- translate :: Char -> Either Char String -> Either Char String -}
{- translate x accum = case x of -}
  {- 'C'       -> fmap (\xs -> 'G' : xs) accum -}
  {- 'G'       -> fmap (\xs -> 'C' : xs) accum -}
  {- 'T'       -> fmap (\xs -> 'A' : xs) accum -}
  {- 'A'       -> fmap (\xs -> 'U' : xs) accum -}
  {- otherwise -> Left x -}

transcribe :: Char -> Either Char Char
transcribe x = case x of
  'C'       -> Right 'G'
  'G'       -> Right 'C'
  'T'       -> Right 'A'
  'A'       -> Right 'U'
  otherwise -> Left x


