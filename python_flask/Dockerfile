FROM python:alpine AS base

FROM base AS builder
COPY requirements.txt /requirements.txt
RUN pip install --user -r /requirements.txt

FROM base
COPY --from=builder /root/.local /root/.local
WORKDIR /app
COPY . .
ENV PATH=/root/.local/bin:$PATH

EXPOSE 5000
CMD ["gunicorn", "-b", "0.0.0.0:5000", "app:app"]