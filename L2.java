import java.util.Scanner; // 입력을 위해 import

class seat // 좌석 기본 함수를 정의할 seat class
{
	public static int plus_count = 0;
	// 좌석 사용자의 수를 나타내는 정적 필드
	// Case2, Case3 클래스에서도 사용하므로 여러번의 객체 생성을 방지하기 위해
	
	public int is_empty(int array[][], int r, int l) // 사용자사 선택한 좌석이 비어있는지 확인하는 함수 정의
	{
		if(array[r][l] != 1) // 누군가 사용하고 있지 않다면
			return 1; // 1 반환
		
		else // 누군가 사용하고 있다면
			return 0; // 0 반환
	}
	
	public static void print_array(int array[][]) 
	// 현재 교실의 좌석을 출력하는 함수
	// 메인 함수와 Case2, Case3 클래스에서도 사용하므로 여러번의 객체 생성을 방지하기 위해 static으로 선언함
	{
		for(int i = 0; i < array.length; i++) // 배열의 행의 개수만큼
		{
			System.out.print("\n"); // 한 행 출력할 때마다 줄바꿈
			for(int j = 0; j < array[0].length; j++) // 배열의 열의 개수만큼 반복
				System.out.print(array[i][j]+" "); // 출력
		}
		System.out.print("\n"); // 가독성을 위한 줄바꿈
			
	}
}


interface CASE23 // case2와 case3에서 모두 사용할 함수의 틀이 정의된 인터페이스.
{
	void free_seat(int seat_array[][], int secret_array[][], int select_r, int select_l);
	// 반환형, 함수의 이름, 매개변수만 정의 가능
}

class CASE2 implements CASE23 // Case2 클래스에서 구현
{
	@Override // 좌석 해제 함수 오버라이드
	public void free_seat(int seat_array[][], int secret_array[][], int select_r, int select_l)
	{
		int tmp_s_num = 0; // 사용자가 현재 좌석의 해제를 위해 입력할 비밀번호를 저장할 필드
		Scanner sc = new Scanner(System.in); // 입력
		if(seat_array[select_r-1][select_l-1] == 1) // 인수로 전달받은 사용자가 입력한 좌석이 1이라면(실제 사용중인 좌석이라면)
 		{
		
			System.out.print("좌석 비밀번호를 입력하세요: ");
			tmp_s_num = sc.nextInt();
			
			if(tmp_s_num == secret_array[select_r-1][select_l-1]) 
			// 현재 사용자가 해제를 위해 입력한 비밀번호와 해당 자리의 비밀번호를 저장해놓은 배열의 값(실제 비밀번호)가 일치한다면
			{
				System.out.println("비밀번호 일치.\n좌석을 해제합니다.");
				seat.plus_count--; // 좌석 추가를 카운트 하는 변수이므로 1만큼 감소
				seat_array[select_r-1][select_l-1] = 0; // 해당 좌석의 사용여부를 0으로 나타냄
				seat.print_array(seat_array); // 현재 상황을 보여주기 위해 좌석 출력함수 호출
			}
			
			else // 사용자가 입력한 좌석이 사용중이지만 비밀번호를 틀린경우
			{
				System.out.println("틀린 비밀번호. 좌석해제 불가."); //인증을 통해 자신의 자리라는 것을 입증할 방법이 없으므로 문구 출력
			}
			
		}
		
		else // 사용자가 입력한 좌석이 미사용중인 좌석일 경우
			System.out.println("미사용중인 좌석입니다."); // 출력
			
	}
}


class CASE3 implements CASE23 // Case3 클래스에서 구현
{
	public static boolean moveFlag; // 이동 여부를 나타내는 필드
	
	
	@Override
	// Case2 클래스와 함수가 비슷하지만 다른 부분이 존재하기 때문에 인터페이스를 사용해서 구현시킴
	// 해제 후 이동시켜야 하기 때문이다.
	public void free_seat(int seat_array[][], int secret_array[][], int select_r, int select_l)
	{
		int tmp_s_num = 0; // 사용자가 현재 좌석의 해제를 위해 입력할 비밀번호를 저장할 필드
		Scanner sc = new Scanner(System.in); // 입력
		if(seat_array[select_r-1][select_l-1] == 1) // 인수로 전달받은 사용자가 입력한 좌석이 1이라면(실제 사용중인 좌석이라면)
		{
			
				System.out.print("좌석 비밀번호를 입력하세요: ");
				tmp_s_num = sc.nextInt();
				
				
				if(tmp_s_num == secret_array[select_r-1][select_l-1])
				// 현재 사용자가 해제를 위해 입력한 비밀번호와 해당 자리의 비밀번호를 저장해놓은 배열의 값(실제 비밀번호)가 일치한다면
				{
					seat_array[select_r-1][select_l-1] = 0; // 다른 것 출력하지 않고 해당 좌석의 사용 여부만을 변경함
				}
				
				else // 사용자가 입력한 좌석이 사용중이지만 비밀번호를 틀린경우
				{
					System.out.println("틀린 비밀번호. 좌석이동 불가."); //인증을 통해 자신의 자리라는 것을 입증할 방법이 없으므로 문구 출력
					moveFlag = false; // 이동 불가능하므로 false로 설정
				}
		}
		
		else // 사용자가 이동을 위해 입력한 현재 좌석이 미사용중인 좌석인 경우
		{	
			System.out.println("미사용중인 좌석입니다."); // 출력
			moveFlag = false; // 이동 불가능하므로 false로 설정
		}
	}	
}

public class L2 { // public class 정의

	public static void main(String[] args) { // 메인 함수
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in); // 입력 객체 생성
		seat mySeat = new seat(); // 기본 함수를 정의한 클래스의 객체 생성
		CASE2 c2 = new CASE2(); // Case2 클래스 인스턴스 생성
		CASE3 c3 = new CASE3(); // Case3 클래스 인스턴스 생성
		
		int r = 0, l = 0; // 전체 행과 열의 크기를 저장할 변수
		boolean program_flag = true; // 주 코드의 무한 루프 여부를 저장할 부울 변수
		
		System.out.println("출석 프로그램");
		// 입력부분
		System.out.print("교실 좌석의 총 행, 열을 입력하세요: ");
		r = sc.nextInt();
		l = sc.nextInt();
		
		int [][]seat_array = new int[r][l]; // 좌석 정보를 저장할 배열
		int [][]secret_array = new int[r][l]; // 비밀번호를 저장할 배열
		
		// 이중 for문을 통해 좌석 미사용으로 초기화
		for(int i = 0; i < r; i++)
		{
			for(int j = 0; j < l; j++)
			{
				seat_array[i][j] = 0; // 빈 자리 0
			}
		}
		
		while(program_flag) // 무한루프
		{
			c3.moveFlag = true; // 일단 true로 설정
			int select_r = 0, select_l = 0, op = 0; // 사용자가 사용, 해제, 이동할 좌석의 좌표를 저장할 변수와 option정보를 저장할 변수
			int s_num,  s_num_check = 0; // 입력받은 비밀번호를 저장할 변수와 더블체크를 위해 한번 더 입력받은 값을 저장할 변수
			
			System.out.print("출석부 프로그램 <1>좌석 선택 <2>좌석 해제 <3>좌석 이동 <4>프로그램 종료 ");
			op = sc.nextInt(); // 입력받기
			
			switch(op)
			{
			case 1:
				System.out.println("1> 좌석 선택");
				
				seat.print_array(seat_array); // 좌석 출력
			
				System.out.println("총 좌석: "+r+"행, "+l+"열");
				// 입력받기
				System.out.print("앉고 싶은 좌석의 행, 열을 입력하세요: ");
				select_r = sc.nextInt();
				select_l = sc.nextInt();
				
				if(select_r < 1 || select_r > r || select_l < 1 || select_l > l) {
				    System.out.println("존재하지 않는 좌석입니다.");
				    break;
				}
				
				// 사용자가 현재 입력한 좌표가 비어있다면
				if(mySeat.is_empty(seat_array, select_r - 1, select_l - 1) == 1)
				{
					do // 입력 번호가 네자리 정수인지 확인하기 위해 일단 실행
					{
						System.out.print("네자리 숫자 비밀번호를 설정해주세요: "); // 비밀번호 입력받기
						s_num = sc.nextInt();
						
						// 네자리 정수인지 검사
						if(s_num < 1000 || s_num > 9999)
							System.out.println("네자리 정수만 입력가능합니다.");
						
					}while(s_num < 1000 || s_num > 9999); // 네자리 정수가 아니면 다시 반복 맞으면 루프 종료
					
					// 네자리 정수면?
					
					int s_num_flag = 1; // 사용자가 초기 비밀번호를 아는지에 대한 여부를 저장하는 변수
					int set_s_num_count = 0; // 사용자가 비밀번호 더블체크를 실행한 횟수를 저장하는 변수
					
					do // 확인 비밀번호가 초기번호와 동일한지 확인
					{
						// 더블체크를 위해 한번 더 입력받기
						System.out.print("설정한 비밀번호를 한번 더 입력해주세요: ");
						s_num_check = sc.nextInt();
						set_s_num_count++;
						
						if(s_num != s_num_check && set_s_num_count > 2) // 확인을 위해 비밀번호를 재입력하는데 3번 이상 틀린 경우 
						{
							int question = 0;
							System.out.print("초기 비밀번호를 모르시겠습니까?(Yes: 1, No: 0): "); // 가이드 문구 출력
							question = sc.nextInt(); // 옵션선택으로 돌아갈지에 대한 여부를 저장할 변수
							
							if(question == 1) // 모른다면(돌아가고 싶다면)
							{
								System.out.println("옵션선택으로 돌아갑니다."); // 문구 출력 후
								s_num_flag = 0; // 비밀번호를 알고있다는 flag를 0으로 변경(모름)
								break; // 비밀번호 입력 반복 종료
							}
							
							else // 더블체크를 위한 비밀번호를 실수로 잘못 입력한 것이라면
								continue; // 계속 반복
						}
						
						
					}while(s_num != s_num_check); // 두 비밀번호가 다르다면 반복. 같다면 루프 종료
					
					
					if(s_num_flag == 1) 
					// do-while문을 탈출했고, 비밀번호를 알고 있다면 
					// 현재 좌석은 비어있고 비밀번호 역시 똑바로 설정된 상황이므로 해당 좌석을 사용중으로 변경한다.
					{
						seat_array[select_r - 1][select_l - 1] = 1;
						secret_array[select_r -1][select_l -1] = s_num; // 비밀번호 배열 동일 인덱스에 비밀번호 저장
						
						System.out.println("좌석 선택 완료");
						seat.plus_count++; // 좌석 선택을 완료했으므로 plus_count를 1만큼 증가
					}
				
				}
				
				else // 이미 사용중인 좌석 처리
					System.out.println("이미 사용중인 좌석입니다."); // 문구 출력
				
				break;
				
			case 2:
				System.out.println("2> 좌석 해제");				
				
				seat.print_array(seat_array); // 현재 좌석 현황을 보여주기 위해 출력
				
				if(seat.plus_count == 0) // plus_count가 0이라면, 즉 좌석 추가를 한 적이 없는데 좌석 해제를 선택한 경우
				{
					System.out.println("사용중인 좌석 없음.");
					break; // switch-case 종료
				}
				
				System.out.println("총 좌석: "+r+"행, "+l+"열");
				// 해제하고 싶은 좌석의 행, 열 입력받기
				System.out.print("해제하고 싶은 좌석의 행, 열을 입력하세요: ");
				select_r = sc.nextInt();
				select_l = sc.nextInt();
				
				// 인덱스 체크
				if (select_r < 1 || select_r > r || select_l < 1 || select_l > l) {
				    System.out.println("존재하지 않는 좌석입니다.");
				    break;
				}
				
				c2.free_seat(seat_array, secret_array, select_r, select_l); // 좌석 해제 함수 호출
				
				break; // switch-case 종료
				
				
			case 3:
				System.out.println("3> 좌석 이동");
				
				seat.print_array(seat_array); // 좌석 현황을 알기위해 행렬 출력
				
				if(seat.plus_count == 0) // 현재 사용중인 좌석이 0개인데 좌석 이동을 입력한 경우
				{
					System.out.println("현재 사용중인 좌석 없음."); // 문구 출력
					break; // 종료
				}
				
				System.out.println("총 좌석: "+r+"행, "+l+"열");
				// 좌표 입력
				System.out.print("현재 좌석의 행, 열을 입력하세요: ");
				select_r = sc.nextInt();
				select_l = sc.nextInt();
				
				// 인덱스 범위 확인
				if (select_r < 1 || select_r > r || select_l < 1 || select_l > l) {
				    System.out.println("존재하지 않는 좌석입니다.");
				    break;
				}
				
				c3.free_seat(seat_array, secret_array, select_r, select_l); // Case3 클래스의 좌석 해제함수 호출
				
				
				
				if(c3.moveFlag != false) // 위에서 좌석 해제를 했는데 좌석 이동 가능 여부를 나타내는 flag가 true라면
				{
					// 이동할 좌석 추가 부분. case1 코드와 동일 기능을 함
					System.out.print("이동하고 싶은 좌석의 행, 열을 입력하세요: ");
					select_r = sc.nextInt();
					select_l = sc.nextInt();
				
					if (select_r < 1 || select_r > r || select_l < 1 || select_l > l) {
					    System.out.println("존재하지 않는 좌석입니다.");
					    break;
					}
					
					if(mySeat.is_empty(seat_array, select_r - 1, select_l - 1) == 1)
					{
						do // 입력 번호가 네자리 정수인지 확인
						{
							System.out.print("네자리 숫자 비밀번호를 설정해주세요: ");
							s_num = sc.nextInt();
							
							if(s_num < 1000 || s_num > 9999)
								System.out.println("네자리 정수만 입력가능합니다.");
							
						}while(s_num < 1000 || s_num > 9999);
						
						// 네자리 정수면?
						
						int case1_flag = 1; // 사용자가 초기 비밀번호를 아는지에 대한 여부를 저장하는 변수
						int set_s_num_count = 0; // 사용자가 비밀번호 더블체크를 실행한 횟루를 저장하는 변수
						do // 확인 비밀번호가 초기번호와 동일한지 확인
						{
							System.out.print("설정한 비밀번호를 한번 더 입력해주세요: ");
							s_num_check = sc.nextInt();
							set_s_num_count++;
							
							if(s_num != s_num_check && set_s_num_count > 2)
							{
								int question = 0;
								System.out.print("초기 비밀번호를 모르시겠습니까?(Yes: 1, No: 0): ");
								question = sc.nextInt();
								
								if(question == 1)
								{
									System.out.println("옵션선택으로 돌아갑니다.");
									case1_flag = 0;
									break;
								}
								
								else // 실수로 잘못 입력했다면
									continue; // 계속 반복
							}
							
							
						}while(s_num != s_num_check);
						
						
						if(case1_flag == 1) 
						// do-while문을 탈출했고, 비밀번호를 알고 있다면 
						// 현재 좌석은 비어있고 비밀번호 역시 똑바로 설정된 상황이므로 해당 좌석을 사용중으로 변경한다..
						{
							seat_array[select_r - 1][select_l - 1] = 1;
							secret_array[select_r -1][select_l -1] = s_num; // 다른 배열 동일인덱스에 비밀번호 저장
							
							System.out.println("좌석 선택 완료");
						}
					
					}
					
					else
						System.out.println("이미 사용중인 좌석입니다.");
				}
				break;
						
			case 4: // 종료 옵션
				System.out.println("프로그램 종료");
				program_flag = false; // 주 프로그램 반복 flag를 false로 변경
				break; // switch-case 함수 종료
			}
			
			
		}
	}

}
