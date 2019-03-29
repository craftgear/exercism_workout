module SecretHandshake (handshake) where

{- #1 recursive ver -}
{- intToBinary :: Int -> [Int] -> [Int] -}
{- intToBinary 0 accum = accum -}
{- intToBinary x accum = (intToBinary (x `quot` 2) accum) ++ x `mod` 2 : accum -}


binary :: Int -> [Int]
binary x = map mod2 $ takeWhile (> 0) $ iterate div2 x
 where
  mod2 = (`mod` 2)
  div2 = (`quot` 2)

patterns = ["wink", "double blink", "close your eyes", "jump", "reverse"]

wordsList n = filter (/= "") $ zipWith fn patterns (binary n)
 where
  fn _    0 = ""
  fn word _ = word

handshake :: Int -> [String]
handshake 0 = []
handshake n = reverseOrNot $ wordsList n
 where
  reverseOrNot xs | elem "reverse" xs = reverse $ init xs
                  | otherwise         = xs
