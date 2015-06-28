<?php 
// EDIT THE 2 LINES BELOW AS REQUIRED
$send_email_to = "mercuryinduction@gmail.com";$email_subject = "Your email subject line";
function send_email($name,$email,$email_message)
{
  global $send_email_to;
  global $email_subject;  $headers = "MIME-Version: 1.0" . "\r\n";
  $headers .= "Content-type:text/html;charset=iso-8859-1" . "\r\n";
  $headers .= "From: ".$email. "\r\n";
  $message = "<strong>Email = </strong>".$email."<br>";
  $message .= "<strong>Name = </strong>".$name."<br>";  
  $message .= "<strong>Message = </strong>".$email_message."<br>";
  @mail($send_email_to, $email_subject, $message,$headers);
  return true;
}
function validate($name,$email,$message){
  $return_array = array();
  $return_array['success'] = '1';
  $return_array['name_msg'] = '';
  $return_array['email_msg'] = '';
  $return_array['message_msg'] = '';
  if($email == '')
  {
    $return_array['success'] = '0';
    $return_array['email_msg'] = 'email is required';
  }
  else
  {
    $email_exp = '/^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/';
    if(!preg_match($email_exp,$email)) {
      $return_array['success'] = '0';
      $return_array['email_msg'] = 'enter valid email.';  
    }
  }  if($name == '')
  {
    $return_array['success'] = '0';
    $return_array['name_msg'] = 'name is required';
  }
  else
  {
    $string_exp = "/^[A-Za-z .'-]+$/";
    if (!preg_match($string_exp, $name)) {
      $return_array['success'] = '0';
      $return_array['name_msg'] = 'enter valid name.';
    }
  }		
  if($message == '')
  {
    $return_array['success'] = '0';
    $return_array['message_msg'] = 'message is required';
  }
  else
  {
    if (strlen($message) < 2) {
      $return_array['success'] = '0';
      $return_array['message_msg'] = 'enter valid message.';
    }
  }
  return $return_array;
}
$name = $_POST['name'];$email = $_POST['email'];
$message = $_POST['message'];

$return_array = validate($name,$email,$message);
if($return_array['success'] == '1'){	send_email($name,$email,$message);}header('Content-type: text/json');echo json_encode($return_array);die();
?>
