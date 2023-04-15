var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop19_faulty { 
	always all f: (Protected - Trash) | eventually f in Trash
}

run  prop19_faulty