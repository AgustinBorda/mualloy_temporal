var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop17_faulty { 
  all f: File |always f in Trash implies always f' not in File and f' not in Trash and f' not in Protected

}

run  prop17_faulty