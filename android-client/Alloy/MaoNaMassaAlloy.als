module maoNaMassa

// TEMPO
open util/ordering[Time] as to
sig Time{}

//SISTEMA
sig Sistema{
	receitas: set Receita -> Time, 
	usuarios: set Usuario -> Time
}

sig Usuario {
	nomeUsuario: one Nome,	
	livro : one Livro
}

sig Livro{
	receitasDoLivro : set Receita ->Time
}

sig Receita{
	ingredientes : set Ingrediente,
	steps : set Step
}

sig Nome{}
sig Ingrediente{}
sig Step{}


// FATOS
fact fatosSistema {
	#Sistema = 1
}

fact fatosReceitas {
all u : Usuario, t : Time | one s  : Sistema | u in (s.usuarios).t
all l : Livro | one u : Usuario | l = u.livro
}


fact traces {
	init[first]
	all pre: Time-last | let pos = pre.next |
		some sm: Sistema, rec: Receita,  u:Usuario |
				(addReceita[sm, u, rec, pre, pos]  or
				cadastraUsuario[sm, u, pre, pos] or addRecNoLivro[sm, u, rec, pre, pos])


}

// Logica temporal

pred init[t: Time] {
	one Sistema
	no (Sistema.receitas).t
	no (Livro.receitasDoLivro).t
}
// PREDICADOS

pred addReceita[sm: Sistema, c: Usuario, r: Receita, t, t': Time] {
	r ! in (sm.receitas).t
	(sm.receitas).t' = (sm.receitas).t + r
} 

pred cadastraUsuario[sm: Sistema, c: Usuario, t, t': Time] {
	c !in (Sistema.usuarios).t
	(Sistema.usuarios).t' = (Sistema.usuarios).t + c
}


pred addRecNoLivro[sm: Sistema, c: Usuario, r: Receita,  t, t': Time] {
	c in (Sistema.usuarios).t
	r in (Sistema.receitas).t
	r !in ((c.livro).receitasDoLivro).t
	(c.livro.receitasDoLivro).t' = (c.livro.receitasDoLivro).t + r
}


// main
pred show[]{}

run show for 3 but  2 Usuario
