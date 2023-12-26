package com.example.mytodoapp.domain.todocard.service

import com.example.mytodoapp.domain.todocard.dto.CreateTodoCardRequest
import com.example.mytodoapp.domain.todocard.dto.TodoCardResponse
import com.example.mytodoapp.domain.todocard.dto.UpdateTodoCardRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TodoCardServiceImpl: TodoCardService {
    override fun getAllTodoCardList(): List<TodoCardResponse> {
        //TODO: DB에서 모든 할일카드를 가져와서 TodoCardResponse로 변환후 리스트로 반환
        TODO("Not yet implemented")
    }


    override fun getTodoCardById(todoCardId: Long): TodoCardResponse {
        //TODO: 만약 todoCardId 해당하는 카드가 없다면 throw ModelNotFoundException
        //TODO: DB에서 할일카드ID에 해당하는 할일카드를 가져와서 TodoCardResponse로 변환후 반환
        TODO("Not yet implemented")
    }

    @Transactional
    override fun createTodoCard(request: CreateTodoCardRequest): TodoCardResponse {
        //TODO: request를 TodoCard로 변환후 DB에 저장
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateTodoCard(todoCardId: Long, request: UpdateTodoCardRequest): TodoCardResponse {
        //TODO: 만약 todoCardId 해당하는 카드가 없다면 throw ModelNotFoundException
        //TODO: DB에서 todoCardId 해당하는 카드를 가져와 request로 업데이트후 DB에 저장,
        // 결과를 TodoCardResponse 변환후 반환
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteTodoCard(todoCardId: Long, password: String) {
        // TODO: 만약 todoCardId 해당하는 카드가 없다면 throw ModelNotFoundException
        // TODO: 만약 password가 일치하지 않으면  throw IncorrectPasswordException
        // TODO: DB에서 todoCardId 해당하는 카드불러와 패스워드를 입력 후 삭제, 연관된 Todo와 Comment도 모두 삭제
        TODO("Not yet implemented")
    }
}