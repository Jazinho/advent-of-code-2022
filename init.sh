day=$( date +%d )

parent_dir="Day $day"

mkdir -p ./$parent_dir/src/com/jpalucki


cp ./"Day $((day-1))"/src/com/jpalucki/"Day$((day-1))Main.java" ./$parent_dir/src/com/jpalucki/"Day${day}Main.java"