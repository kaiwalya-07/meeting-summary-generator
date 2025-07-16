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

        summary = generate_summary(transcript_text)

        result = {
            'fileId': file_id,
            'summaryText': summary
        }

        print(f"Summary: {result}")
        send_summary(result)

    except Exception as e:
        print(f"Error: {e}")
