package jv.triersistemas.primeiro_projeto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

	private static List<Tarefa> tarefas = new ArrayList<>();
	private Long idLocal = (long) 0;
	@GetMapping
	private static List<Tarefa> getTarefas(){
		return tarefas;
	}
	@GetMapping("/{id}")
	private static Tarefa getTarefasId(@PathVariable Long id){
		return tarefas.get(pos(tarefas,id));
	}
	@PostMapping
	public void postTarefas(@RequestParam String titulo, @RequestParam String descricao,@RequestParam boolean completa) {
		Tarefa tarefa = new Tarefa(idLocal,titulo,descricao,completa);
		tarefas.add(tarefa);
		idLocal++;
	}
	@PutMapping
	public void putTarefas(@RequestParam Long id, @RequestParam String titulo, @RequestParam String descricao,@RequestParam boolean completa) {
		Tarefa tarefa = new Tarefa(id,titulo,descricao,completa);
		tarefas.set(pos(tarefas, id), tarefa);
	}
	@DeleteMapping
	public void deleteTarefas(@RequestParam Long id) {
		tarefas.remove(pos(tarefas, id));
	}
	public static int pos(List<Tarefa> tarefa, Long id) {
		return tarefa.indexOf(tarefa.stream().filter(t-> t.getId().equals(id)).findFirst().orElseThrow());
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	class Tarefa {
		private Long id;
		private String titulo;
		private String descricao;
		private boolean completa;
	}
	
}

