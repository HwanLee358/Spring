package com.yedam.app.board.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@AllArgsConstructor // DI lombok
@Controller
public class BoardController {
	@Value("${file.upload.path}") //환경변수 or properties에 기록된 값
	private String uploadPath;
	
	private BoardService boardService;
	
	//DI
	@Autowired
	public BoardController (BoardService boardService){
		this.boardService = boardService;
	}
	
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) {
		List<BoardVO> list = boardService.boardList();
		model.addAttribute("boardList", list);
		return "board/boardList";
	}
	
	// 단건조회 : URI - boardInfo / PARAMETER - BoardVO(QueryString)
	//          RETURN - board/boardInfo
	@GetMapping("boardInfo")
	public String boardInfo(BoardVO boardVO ,Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		return "board/boardInfo";
	}

	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm() {
//		model.addAttribute("board", new BoardVO());
		return "board/boardInsert";
	}
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)
	//             RETURN - 단건조회 다시 호출
	@PostMapping("boardInsert")
	public String boardInsertProcess(@RequestPart MultipartFile[] images, BoardVO boardVO) {
		for(MultipartFile image : images) {
			// 1) 원래 파일이름
			String fileName = image.getOriginalFilename();
			boardVO.setImage(fileName);
			
			// 2) 실제로 저장할 경로를 생성 : 서버의 업로드 경로
			String saveName = uploadPath + File.separator + fileName;
			
			Path savePath = Paths.get(saveName);
			try {
				image.transferTo(savePath);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int bno = boardService.insertBoard(boardVO);
		return "redirect:boardInfo?boardNo="+ bno;
	}
	
	// 수정 - 페이지 : URI - boardUpdate / PARAMETER - BoardVO(QueryString)
	//               RETURN - board/boardUpdate
	// => 등록( 내부에서 수행하는 쿼리문 - UPDATE문 )
	@GetMapping("boardUpdate")
	public String boardUpdateForm(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute(findVO);
		return "board/boardUpdate";
	}
	// 수정 - 처리 : URI - boardUpdate / PARAMETER - BoardVO(JSON)
	//             RETURN - 수정결과 데이터(Map)
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdateProcess(@RequestBody BoardVO boardVO){
		return boardService.updateBoard(boardVO);
	}
	// 삭제 - 처리 : URI - boardDelete / PARAMETER - Integer
	//             RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete")
	public String boardDelete(@RequestParam Integer boardNO) {
		boardService.deleteBoard(boardNO);
		return "redirect:boardList";
	}

}
