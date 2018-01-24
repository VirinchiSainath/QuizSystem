package com.quizsystem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.quizsystem.entity.*;
import com.quizsystem.service.QuizService;
/**
 * Servlet implementation class QuizServlet
 */
@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuizService qs = new QuizService();
		String targetPage=null;
		String action = request.getParameter("action");
		try {
					
			if(action.equals("quizlist")){
				String userRole = (String)request.getSession().getAttribute("userRole");
				if (userRole.equals("professor")){
					int profId = (int)request.getSession().getAttribute("id");
					List<Quiz> qlist = qs.getQuizList(profId);
					request.setAttribute("qList", qlist);
				}
				else {
					List<Quiz> qlist = qs.getQuizList(); // all Quizes
					request.setAttribute("qList", qlist);
					HashMap<Integer, Integer> quizAttempts = new HashMap<>();
					int sId = (int) request.getSession().getAttribute("id");
					
					for(Quiz quiz : qlist){
						Integer quizAttempt = qs.getQuizAttempt(quiz.getId(), sId);
						if (quizAttempt != null){ 
							// quizAttempt has been made 
							// insert score taken insert into hashmap
							quizAttempts.put(quiz.getId(), quizAttempt);
						}
					}
					request.setAttribute("quizAttempts", quizAttempts);
					
				}
				targetPage = "quizes.jsp";				
				
			}
			else if(action.equals("addQuiz")){
				String name = request.getParameter("name");	
				int nOfQues = Integer.parseInt(request.getParameter("nOfQues"));
				int duration = Integer.parseInt(request.getParameter("duration"));
				String showAns = request.getParameter("showAns");
				int profId = (int) request.getSession().getAttribute("id");
				Quiz q = new Quiz(0,name,nOfQues,duration,showAns,profId);
				qs.addQuiz(q);
				
				List<Quiz> qlist = qs.getQuizList(profId);
				request.setAttribute("qList", qlist);
				targetPage = "quizes.jsp";
				
			}
			else if(action.equals("updateQuiz")){
				// updating quiz		
				int quizId = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				int nOfQues = Integer.parseInt(request.getParameter("nOfQues"));
				int duration = Integer.parseInt(request.getParameter("duration"));
				String showAns = request.getParameter("showAns");
						
				qs.updateQuiz(new Quiz(quizId,name,nOfQues,duration, showAns, 0));
				
				//fetching quiz list
				int profId = (int)request.getSession().getAttribute("id");
				List<Quiz> qlist = qs.getQuizList(profId);
				request.setAttribute("qList", qlist);
				targetPage = "quizes.jsp";
			}
			else if(action.equals("editQuiz")){
				int quizId = Integer.parseInt(request.getParameter("id"));
				Quiz q = qs.getQuizTuple(quizId);
				request.setAttribute("quiz", q);
				targetPage = "editQuiz.jsp";
			}
			/*else if(action.equals("submitQandA")){
				int qId = Integer.parseInt(request.getParameter("qid"));
				
				//sending quiz details back again to editQuiz page
				Quiz q = qs.getQuizTuple(qId);
				request.setAttribute("quiz", q);
				
				//question entry and coming back with the list of questions
				String description = request.getParameter("Qdescription");
				int questionId = quesServ.addQuestion(qId, description);
				List<Question> quesList = quesServ.getQuestionList(qId);
				request.setAttribute("quesList", quesList);
				
				//answer entry and comming back with list of ans for this question
				ansServ.addAns(request.getParameter("ans1"), questionId);
				ansServ.addAns(request.getParameter("ans1"), questionId);
				ansServ.addAns(request.getParameter("ans1"), questionId);
				ansServ.addAns(request.getParameter("ans1"), questionId);
				
				//List<Answer> ansList = ansServ.getAnswerList();
				//request.setAttribute("ansList", ansList);
				targetPage="editQuiz.jsp";
				
			}*/
			else if(action.equals("showQandA")){
				// for update quiz form
				int quizId = Integer.parseInt(request.getParameter("quizId"));
				Quiz q = qs.getQuizTuple(quizId);
				request.setAttribute("quiz", q);
				
				//fetching questions in this quiz
				List<Question> quesList = qs.getQuestionList(quizId);
				request.setAttribute("quesList", quesList);			
				
				targetPage = "editQuiz.jsp";				
			}
			/*else if(action.equals("newQues")){
				//code to add a new question with in the quizId acquired
				int quizId = Integer.parseInt(request.getParameter("quizId"));
				String description= request.getParameter("description");
				qs.addQuestion(quizId, description);
				
				//get question list
				List<Question> quesList = qs.getQuestionList(quizId);
				request.setAttribute("quesList", quesList);
				targetPage = "quesList.jsp";
				
			}*/
			else if (action.equals("editQues")){
				//get question from db for the user to change/update
				int questionId = Integer.parseInt(request.getParameter("quesId"));
				Question question = qs.getQuestionDetails(questionId);
				request.setAttribute("thisquestion", question);
				targetPage="editQuestion.jsp";
				
			}
			else if(action.equals("updateQuestion")){
				// making changes to question
				int questionId = Integer.parseInt(request.getParameter("questionId"));
				int quizId = Integer.parseInt(request.getParameter("quizId"));
				String description = request.getParameter("description");
				Question q = new Question(questionId, quizId, description, null);
				
				qs.updateQuestion(q);
				
				// making changes to answers
				List<Integer> ansIds = qs.getAnsIds(questionId);
				
				for(Integer i : ansIds){
					String answerDescription = request.getParameter("descr"+i.toString());
					String correctness = request.getParameter("correctness"+i.toString());
					String correctAnswer= null;
					if(correctness!=null){
						correctAnswer = "Y";
					}
					else {
						correctAnswer = "N";
					}
					qs.updateAnswer(i, answerDescription, correctAnswer);
				}
				
				//setting targetPage
				String newAnswer = request.getParameter("newAnswer");
				if(newAnswer == ""){
					// no new answer is added. so should be directed to the list of questions. before directing to the list of questions
					// need to calculate list of questions
					
					//get question list and quiz (edit quiz also requires quiz object
					Quiz quiz = qs.getQuizTuple(quizId);
					request.setAttribute("quiz", quiz);
					List<Question> quesList = qs.getQuestionList(quizId);
					request.setAttribute("quesList", quesList);
					targetPage = "editQuiz.jsp";
					
				}
				else {
					// new answer has been entered and this answer has to be updated in the database
					// and we should direct the user to the same page with the updated list of answers
					
					//adding answer to db
					qs.addAnswer(questionId, newAnswer);
					
					// before directing to the same page updated question details we need first fetch the details
					Question question = qs.getQuestionDetails(questionId);
					request.setAttribute("thisquestion", question);
					targetPage = "editQuestion.jsp";
				}
			}
			else if(action.equals("deleteQues")){
				int questionId = Integer.parseInt(request.getParameter("quesId"));
				int quizId = Integer.parseInt(request.getParameter("quizId"));
				
				qs.deleteQuestion(questionId);
				
				//get question list
				List<Question> quesList = qs.getQuestionList(quizId);
				request.setAttribute("quesList", quesList);
				targetPage = "quesList.jsp";
			}
			else if(action.equals("addNewQuestion")){
				// first add new question to db
				int quizId = Integer.parseInt(request.getParameter("quizId"));
				String description = request.getParameter("description");
				int questionId = qs.addQuestion(quizId, description);				
				
				// then check if new answer is added if yes add that to db and direct it to editQuestion 
				//or else direct it to quesList
				String newAnswer = request.getParameter("newAnswer");
				if(newAnswer == ""){
					// no new answer is added. so should be directed to the list of questions. before directing to the list of questions
					// need to calculate list of questions
					
					//get question list and quiz (edit quiz also requires quiz object
					Quiz quiz = qs.getQuizTuple(quizId);
					request.setAttribute("quiz", quiz);
					List<Question> quesList = qs.getQuestionList(quizId);
					request.setAttribute("quesList", quesList);
					targetPage = "editQuiz.jsp";
					
				}
				else {
					// new answer has been entered and this answer has to be updated in the database
					// and we should direct the user to the same page with the updated list of answers
					
					//adding answer to db
					qs.addAnswer(questionId, newAnswer);
					
					// before directing to the same page updated question details we need first fetch the details
					Question question = qs.getQuestionDetails(questionId);
					request.setAttribute("thisquestion", question);
					targetPage = "editQuestion.jsp";
				}
				
			}
			else if (action.equals("takeQuiz")){//start quiz for student
				int quizId=Integer.parseInt(request.getParameter("quizId"));
				List<Question> questionList = qs.getQuestionList(quizId);
				
				//insert into QuesSubmission object
				List<QuesSubmission> quesSubList = new ArrayList<>();
				QuesSubmission.setQuizId(quizId);
				for(Question q : questionList){
					quesSubList.add(new QuesSubmission(q.getId(),q.getDescription()));
				}
				
				request.getSession().setAttribute("quesSubList", quesSubList);
				int index = 0;
				request.setAttribute("index", index);
				request.setAttribute("quesSub", quesSubList.get(index));
				List<Answer> ansList = qs.getAnswerList(quesSubList.get(index).getQuestionId());
				request.setAttribute("ansList", ansList);
				request.getSession().setAttribute("size", quesSubList.size());
				targetPage="startQuiz.jsp";
				
			}
			else if (action.equals("loadQuestion")){
				//first check if the request has marked answer and then load next question
				List<QuesSubmission> quesSubList = (List<QuesSubmission>) request.getSession().getAttribute("quesSubList");
				int index = Integer.parseInt(request.getParameter("index"));
				String button = null;
				if(request.getParameter("next") != null){
					button = "next";
				}
				else if (request.getParameter("prev") != null){
					button = "prev";
				}
				else if (request.getParameter("finish") != null){
					button = "finish";
				}
				else if (request.getParameter("review") != null){
					button = "review";
				}
				QuesSubmission currQuesSub = quesSubList.get(index);
				
						
				//getting ans ids from db
				List<Integer> ansIds = qs.getAnsIds(currQuesSub.getQuestionId()); // all the ans ids
				
				List <Integer>	studResp = new ArrayList<Integer>();
				for(Integer i : ansIds){
					String markedAns = request.getParameter("correctness"+i.toString());
					if(markedAns!=null){
						studResp.add(i);
					}
				}
				currQuesSub.setAnsList(studResp);
				currQuesSub.setVisited(true);
				
				
				QuesSubmission loadQuesSub = null;
				// loading the next question on if prev or next button is pressed
				if(button.equals("next") || button.equals("prev")){
					if(button.equals("next")){
						loadQuesSub = quesSubList.get(++index);	
					}
					else{
						loadQuesSub = quesSubList.get(--index);
					}
								
					request.setAttribute("quesSub", loadQuesSub);
					request.setAttribute("index", index);
					
					//get answerList for this questionId and set answer list
					List<Answer> ansList = qs.getAnswerList(loadQuesSub.getQuestionId());
									
					//mark earlier responses for this ans list
					List <Integer> earlierStudResp = loadQuesSub.getAnsList();
					if(earlierStudResp.size() != 0){
						//that means there are earlier student responses
						for(Answer a : ansList){
							if(earlierStudResp.contains(a.getId())){
								a.setCorrectness("Y");
							}
						}
					}
					request.setAttribute("ansList", ansList);					
					targetPage = "startQuiz.jsp";
				}
				else if(button.equals("review")){
					
				}
				else if(button.equals("finish")){
					//calculate results and show results
					
					//calculation results. studResponses are stored in quesSubList 
					// we will need verify for each stud responses
					int score = 0;
					for(QuesSubmission quesSubItr : quesSubList){
						List<Integer> corrAns = qs.getCorrectAnswers(quesSubItr.getQuestionId());
						List<Integer> studAns = quesSubItr.getAnsList();
						
						if(corrAns.equals(studAns)){
							score ++;
						}
					}
					request.setAttribute("score", score);
					request.setAttribute("maxQ", quesSubList.size());
					// save score in db
					qs.saveResult(QuesSubmission.getQuizId(), (int)request.getSession().getAttribute("id"), score);
					targetPage = "result.jsp";
				}
				
			}
			qs.close();
			request.getRequestDispatcher(targetPage).forward(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
