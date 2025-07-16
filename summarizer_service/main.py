from app.consumer import get_kafka_consumer
from app.summarizer import generate_summary
from app.producer import send_summary

consumer = get_kafka_consumer()

for message in consumer:
    try:
        data = message.value
        print(f"Received transcript: {data}")

        transcript_text = data['transcriptText']
        file_id = data['fileId']

        result = generate_summary(transcript_text)

        payload = {
            'fileId': file_id,
            'summaryText': result['summary'],
            'actionItems': result['actionItems'],
            'questions': result['questions']
        }

        print(f"Extracted result: {payload}")
        send_summary(payload)

    except Exception as e:
        print(f"Error: {e}")
