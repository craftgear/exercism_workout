module School (School, add, empty, grade, sorted) where

import           Control.Arrow (second)
import qualified Data.Map      as M
import qualified Data.Set      as S

type Grade = Int
type Student = String
newtype School = School {unSchool :: M.Map Grade (S.Set Student)}


add :: Int -> String -> School -> School
add gradeNum student =
  School . M.insertWith S.union gradeNum (S.singleton student) . unSchool

empty :: School
empty = School M.empty

grade :: Int -> School -> [String]
grade gradeNum = S.toAscList . M.findWithDefault S.empty gradeNum . unSchool

sorted :: School -> [(Int, [String])]
sorted = map (second S.toAscList) . M.toAscList . unSchool
