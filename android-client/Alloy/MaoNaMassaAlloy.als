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
	dono : one Usuario,
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

fact fatosPessoas {
	all n: Nome | one n.~nomeUsuario
}

fact fatosReceitas {
	all l : Livro | one l.~livro
}


fact traces {
	init[first]
	all pre: Time-last | let pos = pre.next |
		lone sm: Sistema, rec: Receita,  u:Usuario |
				(addReceita[sm, u, rec, pre, pos]  or
				cadastraUsuario[sm, u, pre, pos] or addRecNoLivro[sm, u, rec, pre, pos])


}





// Logica temporal

pred init[t: Time] {
	one Sistema
	
	
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



pred show[]{}

run show for 5

//assinaturas (conjuntos e relações)
//fatos (invariantes)
//predicados e funções
//asserções
//run
//check

//Definição de 5 assinaturas, pelo menos uma herança
//Definição de 5 predicados e 3 funções
//Definição de 5 operações que simulam o comportamento temporal do sistema (usar assinatura Time)
//Definição e verificação de 3 asserts (testes sobre o sistema)
